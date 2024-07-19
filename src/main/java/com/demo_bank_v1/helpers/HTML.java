package com.demo_bank_v1.helpers;

public class HTML {
    public static String htmlEmailTemplate(String token,int code){

        //Verify Account URL:
        String url="http://127.0.0.1:8070/verify?token="+token+"&code="+code;

        String emailTemplate="<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <!-- <link rel='stylesheet' href='css/email.css'> -->\n" +
                "    <link rel='preconnect' href='https://fonts.googleapis.com'>\n" +
                "    <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>\n" +
                "    <link href='https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap' rel='stylesheet'>\n" +
                "    <title>email</title>\n" +
                "    <style>\n" +
                "        *{\n" +
                "            box-sizing: border-box;\n" +
                "            font-family: 'Montserrat', sans-serif;\n" +
                "            font-optical-sizing: auto;\n" +
                "            font-style: normal;\n" +
                "            text-align: justify;\n" +
                "        \n" +
                "        }\n" +
                "        body{\n" +
                "            height: 100vh;\n" +
                "            background-color: rgb(212,222,230);\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        \n" +
                "        }\n" +
                "        .wrapper{\n" +
                "            width: 550px;\n" +
                "            height: auto;\n" +
                "            padding: 15px;\n" +
                "            background-color: white;\n" +
                "            border-radius: 7px;\n" +
                "        }\n" +
                "        .email-msg-header{\n" +
                "            font-size: 25px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .company-name{\n" +
                "            width:100%;\n" +
                "            font-size: 35px;\n" +
                "            color: grey;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .welcome-text{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .verify-account-btn{\n" +
                "            padding:15px;\n" +
                "            background-color: rgb(0,109,252);\n" +
                "            text-decoration: none;\n" +
                "            color: white;\n" +
                "            border-radius: 5px;\n" +
                "           margin-left: 180px;\n" +
                "        }\n" +
                "        .copy-right{\n" +
                "            padding: 15px;\n" +
                "            color: grey;\n" +
                "            font-size: 14px;\n" +
                "            margin: 20px 0px;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "        \n" +
                "        \n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "   \n" +
                "<div class='wrapper'>\n" +
                "    <h2 class='email-msg-header'>\n" +
                "        Welcome and Thank You for Choosing<br>\n" +
                "    </h2>\n" +
                "    <div class='company-name'>Easy Way Bank</div>\n" +
                "    <hr>\n" +
                "\n" +
                "    <p class='welcome-text'>\n" +
                "        Your account has been successfully registered, please click below to very your account.\n" +
                "    </p>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <a href='"+url+"' class='verify-account-btn' role='button'>Verify Account</a>\n" +
                "\n" +
                "    <div class='copy-right'>\n" +
                "        &copy; Copy Right 2021. All Right Reserveds.\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        return emailTemplate;
    }
}
