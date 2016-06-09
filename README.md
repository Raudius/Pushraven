# Pushraven

A simple library to easily send notifications over Firebase Cloud Messaging (FCM). 
The library was built with the purpose of making the process of sending messages as simple and modular as posible.

## How to use Pushraven

Once you have imported the Pushraven_vX.X.jar file into your library and the com.pushraven.* package into your class, you can send a push message over FCM in 3 steps:

### 1. Create a Pushraven object with your FCM server-key in the constructor.
```
Pushraven raven = new Pushraven(my_key);
```

### 2. Build your message using paramters from the FCM protocol
FCM Protocol refference: https://firebase.google.com/docs/cloud-messaging/http-server-ref
```
raven.title("MyTitle")
  .text("Hello World!")
  .color("#ff0000")
  .addTarget(client_key);
```
Some attributes may not have been added to Pushraven yet, so you can use the method: addAttribute(parameter, value):
```
raven.addAttribute("delay_while_idle", true);
```

### 3. Send the raven
```
raven.push();
```
  
  
  
#### 3.5 Clearing the raven
You can use the clear() methods to ready the raven for a new notification:
```
raven.clear(); // clears the notification, equatable with "raven = new Pushraven();"
raven.clearAttributes(); // clears FCM protocol paramters excluding targets
raven.clearTargets(); // only clears targets
```
