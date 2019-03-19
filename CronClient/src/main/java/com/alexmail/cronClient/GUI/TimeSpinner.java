package com.alexmail.cronClient.GUI;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

class TimeSpinner {
    static JSpinner getSpinner() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        SpinnerDateModel dateModel = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.HOUR_OF_DAY);
        JSpinner spinner = new JSpinner(dateModel);
        JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "dd.MM.yyyy HH:mm:ss");
        spinner.setEditor(de);
        return spinner;
    }
}
