
![[Pasted image 20250316130212.png]]

![[Pasted image 20250316130524.png]]


#{shouldOrder == true}
## Java-Delegate

* Synchron

```java
@Component  
public class ExecuteOrderDelegate implements JavaDelegate {  
  
    private final Logger LOGGER = LoggerFactory.getLogger(ExecuteOrderDelegate.class);  
  
    @Override  
    public void execute(DelegateExecution delegateExecution) {  
        LOGGER.info("Executing ExecuteOrderDelegate");  
    }  
}
```


# Pool / Particant

Nur visuelle Bedeutung 