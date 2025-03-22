
Neue Form deployen

![[Pasted image 20250316131014.png]]

## Runtime-Service

* Im Projekt holen wir uns Einträge aus der Datenbank / Queue und starten auf diese Weise
* Auch Möglich über das Rest-Api den Startpunkt zu triggern.


```java
@Component  
public class CallNotifyProcessDelegate implements JavaDelegate {  
  
    private final Logger LOGGER = LoggerFactory.getLogger(CallNotifyProcessDelegate.class);  
  
    private final RuntimeService runtimeService;  
  
    public CallNotifyProcessDelegate(RuntimeService runtimeService) {  
        this.runtimeService = runtimeService;  
    }  
  
    @Override  
    public void execute(DelegateExecution delegateExecution) {  
        LOGGER.info("NotifyProcessDelegate: {}", "message");  
        Object message = delegateExecution.getVariable("message");  
  
        LOGGER.info("NotifyProcessDelegate: {}", message);  
  
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();  
        stringObjectHashMap.put("message", message);  
  
        runtimeService.startProcessInstanceByMessage("message_notify", stringObjectHashMap);  
    }  
}
```

![[Pasted image 20250316131146.png]]

```java
@Component  
public class NotifyDelegate implements JavaDelegate {  
  
    private final Logger LOGGER = LoggerFactory.getLogger(NotifyDelegate.class);  
  
    @Override  
    public void execute(DelegateExecution delegateExecution) {  
        LOGGER.info("NotifyProcessDelegate: {}", delegateExecution.getVariable("message"));  
    }  
}
```

