# Teaching-HEIGVD-RES-2021-Labo-SMTP

## <u>Description</u>

The initial idea of the project is to teach the student the behavior of the SMTP protocol and how to use it.

Thanks to this application, we can now prank our friends.

Because our application is a mail prank generator. To avoid spamming our friends, we have set up a mock server that will take care of retrieving the mails before they leave.

#### How does it work ? 

The application will randomly take the emails you propose and randomly take a message you propose, then it will randomly choose between the chosen emails to determine the victim that will send the email and the rest of the emails will be the victims receiving the email. 

## <u>Instructions to set up mock SMTP server</u>



## <u>Instructions to configure our tool</u>

First, in order to use our application, you will have to clone our repo.

To be able to use our application, you just have to modify three different files to adapt what you want :

- You can change the application's configuration here : `SMTPrank\src\main\resources\config.properties`
- You can change the prank messages here : `SMTPrank\src\main\resources\messages.utf8`
- You can change the list of emails here : `SMTPrank\src\main\resources\victims.utf8`

And then to run the app, you'll need to run the class `SMTP/src/main/java/ch/heigvd/MelMot/Main.java`


## <u>Implementation</u>

![UML](.\figures\uml.jpg)

