package com.example.testversion002;

public class Numbers {
    String arduinos_number;
    String users_number;
    public Numbers()
    {
        arduinos_number = "";
        users_number = "";
    }
    public String GetUNumb()
    {
        return users_number;
    }

    public String GetANumb()
    {
        return arduinos_number;
    }
    public void GetUNumb(String number)
    {
        users_number = number;
    }

    public void GetANumb(String number)
    {
        arduinos_number = number;
    }
}

