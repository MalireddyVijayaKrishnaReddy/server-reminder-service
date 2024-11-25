package com.project.schedulemanager.server_reminder_service.dao;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;

import java.util.Locale;

public class delete {
    public static void main(String[] args) {
        String phoneNumber = "4059233451"; // Replace with the phone number you want to check

        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber parsedNumber = phoneUtil.parse(phoneNumber, "US"); // Replace "US" with the appropriate region code

            PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();
            String carrierName = carrierMapper.getNameForNumber(parsedNumber, Locale.ENGLISH); // Replace "en" with the desired language code

            System.out.println("Carrier: " + carrierName);
        } catch (NumberParseException e) {
            System.err.println("Error parsing phone number: " + e.getMessage());
        }
    }
}
