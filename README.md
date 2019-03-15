# Pushraven

A library to easily send notifications over Firebase Cloud Messaging (FCM). 
The library was built with the purpose of making the process of sending messages as simple and modular as posible.

### Legacy API
FCM has had a total makeover for the new "REST v1 API". I have decided not to include backwards compatibility in Pushraven.<br/>
**If you wish to use the (simpler) legacy API see [Legacy Instructions](Legacy.md)**

## How to use Pushraven

### 0. Import Pushraven to your Project.
**NOTE: New package name to remove redundant 'pushraven' in groupId!** <br/>
Add Pushraven.jar and it's dependencies to your project. Or add Pushraven as a dependency: <br/>
**Maven:**
```
<dependency>
  <groupId>us.raudi</groupId>
  <artifactId>pushraven</artifactId>
  <version>1.0.2</version>
</dependency>
```
**Gradle:**
```
compile group: 'us.raudi', name: 'pushraven', version: '1.0.2'
```


### 1. Give Pushraven your Project ID and your Service Account
Both of these can be found in your Firebase console, under Project Settings:
 * The ID is found in the "General" tab
 * The Service Account JSON file can be downloaded from the "Service Account" tab.
```
Pushraven.setCredential(new File("service_account.json");	
Pushraven.setProjectId("fcmtest-f57d4");
```

### 2. Build your 'Message' using parameters from the [FCM reference](https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)<sup>[1]</sup>
In Pushraven all the JSON Objects from the API are implemented as classes, and all fields are implemented as methods.
  
#### 2.1 Create Notification
```
Notification not = new Notification()
		.title("Hello World")
		.body("This is a notification");
```
  
  
#### 2.2 (Optional) Create target specific configurations.
```
AndroidConfig droidCfg = new AndroidConfig()
			.notification(
				new AndroidNotification()
				.color("#ff0000")
			)
			.priority(Priority.HIGH);
```
  
  
#### 2.3 Create the Message (using Notification and any configs)
```
Message raven = new Message()
		.name("id")
		.notification(not)
		.token(CLIENT_ID) // could instead use: topic(String) or condition(String)
		.android(droidCfg);
```


##### NOTE: Missing attributes
If the API updates and implements new fields to any class, these may not have been added to Pushraven yet.
You can use the following methods (for any of the constructor classes: Message, Notification, config classes...):
```
addAttribute(String key, Object value);
addAttributeMap(String key, Map<?, ?> map);
addAttributeArray(String key, Collection<?> arr);
addAttributePayload(String key, Payload payload); // see: Payload.java
```

### 3. Send the raven
```
Pushraven.push(raven);
// or (if you want to access the response)
FcmResponse response = Pushraven.push(raven);
```




[1] https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages