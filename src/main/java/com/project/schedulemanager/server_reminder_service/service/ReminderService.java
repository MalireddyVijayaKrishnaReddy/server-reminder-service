package com.project.schedulemanager.server_reminder_service.service;

import com.project.schedulemanager.server_reminder_service.dao.DemoGraphicsDao;
import com.project.schedulemanager.server_reminder_service.dao.ReminderArchieveDao;
import com.project.schedulemanager.server_reminder_service.dao.ReminderDao;
import com.project.schedulemanager.server_reminder_service.entity.Reminder;
import com.project.schedulemanager.server_reminder_service.entity.ReminderArchieve;
import com.project.schedulemanager.server_reminder_service.templates.EmailTemplate;
import com.project.schedulemanager.server_reminder_service.templates.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReminderService {
    EmailService emailService;
    SMSService smsService;
    ReminderDao reminderDao;
    ReminderArchieveDao reminderArchieveDao;
    DemoGraphicsDao demoGraphicsDao;
    private static final String PHONECOUNTRYCODE="+1";


    @Autowired
    ReminderService(EmailService emailService, SMSService smsService, ReminderDao reminderDao, ReminderArchieveDao reminderArchieveDao, DemoGraphicsDao demoGraphicsDao) {
        this.emailService = emailService;
        this.smsService = smsService;
        this.reminderDao = reminderDao;
        this.reminderArchieveDao = reminderArchieveDao;
        this.demoGraphicsDao = demoGraphicsDao;

    }


    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void checkReminders() {
        System.out.println("Crown Job Triggered");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime max_due_date = now.plusWeeks(1);


        List<Reminder> reminders = reminderDao.getAllReminders(max_due_date);
        System.out.println(reminders);

        for (Reminder reminder : reminders) {
            try {
                LocalDateTime triggerTime = getTriggerTime(reminder.getReminder_datetime(), reminder.getRemind_me());

                // If the reminder time to trigger is now, print the reminder
                if (triggerTime.isBefore(now)) {
                    this.sendReminder(reminder);
                    System.out.println("Reminder for user: " + reminder.getUsername() + " - Task: " + reminder.getTask_name() + " - Reminder time: " + reminder.getReminder_datetime() + " - Notice: " + reminder.getRemind_me());
                }
            }catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public void sendReminder(Reminder reminder) {
        //System.out.println(reminder.getReminder_medium());
        if (reminder.getReminder_medium().contains("EMAIL")) {


            EmailTemplate emailTemplate = getEmailTemplate(reminder);
            emailService.sendEmail(emailTemplate, reminder.getUsername());
        }
        /// send SMS
        if (reminder.getReminder_medium().contains("SMS")) {
            MessageTemplate messageTemplate = new MessageTemplate(reminder);

           // System.out.println(smsService.sendMessage(messageTemplate.getBody(),"+14699906715"));
            String phoneNumber = PHONECOUNTRYCODE+demoGraphicsDao.getUserDemographics(reminder.getUsername())
                                                                 .getPhone_number();
            System.out.println(phoneNumber);
            smsService.sendMessage(messageTemplate.getBody(),phoneNumber);
        }
        reminder.setReminder_sent("Y");
        reminderDao.updateReminder(reminder);

    }

    private EmailTemplate getEmailTemplate(Reminder reminder) {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject(reminder.getCategory_name());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTime = reminder.getReminder_datetime().format(formatter);

        String emailBody = "Your " + reminder.getTask_name() + " is due on " + dateTime;
        emailTemplate.setBody(emailBody);
        return emailTemplate;
    }

    private LocalDateTime getTriggerTime(LocalDateTime reminderTime, String remindMe) {
        switch (remindMe) {
            case "1W":
                return reminderTime.minusWeeks(1);
            case "1D":
                return reminderTime.minusDays(1);
            case "2H":
                return reminderTime.minusHours(2);
            case "1H":
                return reminderTime.minusHours(1);
            case "30M":
                return reminderTime.minusMinutes(30);
            case "15M":
                return reminderTime.minusMinutes(15);
            default:
                throw new IllegalArgumentException("Unknown reminder type: " + remindMe);
        }
    }
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void archivePastReminders() {
        List<Reminder> pastReminders = reminderDao.getExpiredReminders(LocalDateTime.now());


        pastReminders.forEach(reminder -> {
           ReminderArchieve reminderToArchieve = new ReminderArchieve();
            Class<?> reminderClass = reminder.getClass();
            Class<?> reminderArchieveClass = reminderToArchieve.getClass();
            for (Field sourceField : reminderClass.getDeclaredFields()) {
                try {
                    sourceField.setAccessible(true);
                    Field targetField = reminderArchieveClass.getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true);
                    targetField.set(reminderToArchieve, sourceField.get(reminder));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // Handle the exception or log it if necessary
                }
            }
            reminderDao.removeReminder(reminder);
            //System.out.println("......."+reminderToArchieve);
            reminderArchieveDao.archieveReminder(reminderToArchieve);
        });
    }



}
