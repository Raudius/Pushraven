# Pushraven

A simple library to easily send notifications over Firebase Cloud Messaging (FCM). 
The library was built with the purpose of making the process of sending messages as simple and modular as posible.

## How to use Pushraven

**NOTE: Package name change from com.raudius.pushraven to us.raudi.pushraven for release version 1.0.1!**

### 0. Import Pushraven to your Project.
Add Pushraven.jar and json-simple.jar to your project. Or add Pushraven as a Maven dependency:
```
<dependency>
  <groupId>us.raudi.pushraven</groupId>
  <artifactId>Pushraven</artifactId>
  <version>1.0.2</version>
</dependency>
```

### 1. Give Pushraven your FCM Server Key.
```
Pushraven.setKey(my_key);
```

### 2. Build your 'Notification' using parameters from the FCM reference<sup>[1]</sup>
```
Notification raven = new Notification();
raven.title("MyTitle")
  .text("Hello World!")
  .color("#ff0000")
  .to(client_key);
```
Some attributes from the specification may not have been added to Pushraven yet, so you can use the following methods:
```
raven.addRequestAttribute("delay_while_idle", true); // for request attributes
raven.addNotificationAttribute("color", "#ff0000"); // for notification attirubtes
```
Request attributes are those in table 1 from the specification.
Notification attributes are those in tables 2a (iOS) and 2b (Android)
<br /><br />
NOTE: you may also use the static Notification from Pushraven: 
```
Pushraven.notification.title("title")
  .to("clients")
  ...
```

### 3. Send the raven
```
Pushraven.push(raven);

// or 
Pushraven.setNotification(raven); // if not already set
Pushraven.push();
```

<br /><br />
  
  
#### 3.5 Clearing the raven
You can use the clear() methods to ready the raven for a new notification:
```
raven.clear(); // clears the notification, equatable with "raven = new Notification();"
raven.clearAttributes(); // clears FCM protocol paramters excluding targets
raven.clearTargets(); // only clears targets
```
NOTE: Clearing the raven is more efficient than creating a new object. As creating a new object will cause Java Garbage Collector to delete the old object.

<br /><br />


[1] https://firebase.google.com/docs/cloud-messaging/http-server-ref
