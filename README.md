# ServiceBehaviour
This is a readme for the code in first commit
----------Service intro------------------------
so Service is one of the 4 main components of android.
Services works in background
but it does'nt mean they work on separate tread ,instead the work on the main thread,they just don't have any UI.
since they work on main thread this could mean they can block a main UI thread.
but don't worry they are not mean't to be put to kind of work that can block the main thread.
instead they are used when user don't need engaged to engage the UI ,so no UI not blockage.
one other way to do heavy work with service is start a new thread inside the service.
so the work will be done on sperate thread hence no UI blockage.
Service needs to be registerd in the manifest file.

-----------Forms of service-------------
-their are two forms of Service
-a)Start Service: 
                when some other component of app makes a call to start a service.
-b)Bond Service:
                when component of an app binds itself to a service then this service is called Bond Service. this service provides 
                updates to the binded component
                
----------------How to make a service(Start Service)---------
-make a subclass of the Service class
-register Service in manifest.
-and override few methods ,which includes onCreate,onDestroy,onBind,onStartCommand.
-in main activity make intent and call startService(intent) method.
-when service starts the first method which executes is onCreate() then onStartCommand.
-to stop service from inside service simple call methode stopSelf().
-Service can also be stopped by Activity or outside by simple calling method stopService(intent);
-the onStartCommand method returns an int Constant.
-value returned by onStartCommand is important for the life cycle of the Service.
-it can return any of these three Constants.
-START_STICKY ,START_NOT_STICKY,START_REDILIVER_INTENT.
-START_STICKY : if this value is returned ,the service will still continue to work in background even if app is closed
           in other words process is kept alive. even if os kills the service, it will respon it self when resourses are available.
            but every time service starts itself Service will create a new instance .means old values are lost.
-START-NOT-STICKY : This service will be killed once the app is killed and it will not respone itself.
-onDestroy() : this will be called if service is stopped from inside the service using stopself() or from outside using stopService(intent).
-sometimes if works is heavy eg it takes more than 5 seconds ,in this case we make a new thread inside the onStartCommand() method
 and hand over the heavy work to runnable obj to pass into that thread. so heavy work is done is separate thread.
--------------Intent Service----------------------------------
so a normal service works on the UI/main thread unless we creates a separate thread in it and put our work in that separate thread.
Intent Service makes this easy for us as it works by default on the separate thread ,so it save's us from all that extra work of creating and managing a separate thread our self.
on other diffrence that it has to a normal thread is that, it will stop it self once the task is completed,a normal service don't stop
it self until we explicitly tell it to.
only onHandleIntent() method works on the separte thread,the rest of methods still works on the main thread.
their is one confusion though ,if we exit the app before the Intent service completes its task then the intentService will destroy it self after completion but process of that app is not killed even though app is not alive and Servie is destroyed.
