## Beispiel JobWorker

```java
@ZeebeWorker(type = "retrieveMoney")
public void retrieveMoney(final JobClient client, final ActivatedJob job) {
}
```

