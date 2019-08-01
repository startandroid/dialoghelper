
# Dialog Helper

A small kotlin library that helps you to create a simple android dialog and handle results.

Features:
- can handle results even after screen rotation
- can be used from Activity and Fragment


## Download

[ ![Download](https://api.bintray.com/packages/startandroid/dialog-helper/dialog-helper/images/download.svg) ](https://bintray.com/startandroid/dialog-helper/dialog-helper/_latestVersion)

Add jcenter repository into project's build.gradle file
``` groovy
buildscript {
    repositories {
        jcenter()
		...        
    }
    ...
}
``` 

Gradle dependency:
``` groovy
implementation 'com.startandroid.dialog-helper:dialog-helper:x.x.x'
```   

## How to use

Create or inject the DialogHelper object. It should have the same scope as Activity/Fragment
``` kotlin
private val dialogHelper = DialogHelper()
```

\
Then you need 3 steps to create and show dialog:

### 1. Create dialog
In OnCreate method of your Activity/Fragment create DialogConfig and register it in DialogHelper:

``` kotlin
override fun onCreate(savedInstanceState: Bundle?) {  
    super.onCreate(savedInstanceState)  
  
    // ...  
  
  val dialogConfig = DialogConfig()  
        .message(R.string.delete_file_question)  
        .positive(R.string.yes) { deleteFile() }  
        .negative(R.string.no) // do nothing 
  
  dialogHelper.registerDialogConfig(1, dialogConfig)  
}
```
Number 1 here is a dialog code. It's id of dialog.  
We set action (calling deleteFile() method) that will be invoked when user press Yes button.   
For No button we set no any actions.   
We skipped operators title and neutralButton because we don't need title and Cancel button.  

### 2. Show dialog
When you need to show the dialog, call method showDialog and pass your dialog code and your current Activity/Fragment:
``` kotlin
dialogHelper.showDialog(1, this)
```

### 3. Handle dialog's buttons
If you have set some actions for buttons, you need to add handling for dialog's buttons. For this your Activity/Fragment should implement interface HasDialogHandler with method dialogHandler, in which you just return your DialogHelper object:
``` kotlin
class MainFragment : Fragment(), HasDialogHandler {
    // ...
    override fun dialogHandler(): DialogHandler? = dialogHelper
    // ...
```
When user press button on the dialog, DialogHelper will get it and will invoke your action that you have set in DialogConfig. It will work even after screen rotation.


## FAQ

Q: Why should i register dialog configs in onCreate method, and not right before showing a dialog?   
A: Because of handling results after screen rotating. For example, you showed a dialog and then rotate the screen. Right after rotating the method onCreate will be called and handlers will be registered in DialogHelper.
