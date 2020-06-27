# FoodScanner-App
Food data are obtained from a web service, to launch the web service that generates a random feed, run the following commands in a terminal :
            cd /tmp
            wget https://pageperso.lif.univ-mrs.fr/~bertrand.estellon/androidCCI/WebService.tgz 
            tar xzf WebService.tgz
            cd WebService
            php -S localhost:8000
            
As in PHP, you must let the web service run so that it listens on port 8080 of the host machine. Try accessing the URL http://localhost:8000/food/random 
to see if the web service is working.
If you are using Windows or MacOS, you may need to replace localhost with 0.0.0.0 in the command that runs the web service 
(php -S 0.0.0.0:8080 instead of php -S localhost:8000) for the emulator to access the web service.

Test the application with the barcodes after starting the web service. 
