package tracing.aspects;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class ThreadCountingAspect {

    Set<String> workingThreads = ConcurrentHashMap.newKeySet();

 
  //  @Pointcut("execution(* (@org.springframework.web.bind.annotation.RestController *).*(..))") 
    public void monitored() {
    }

  //  @Around("monitored()")
    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass().getName());
        workingThreads.add(Thread.currentThread().getName());
        long start = System.currentTimeMillis();

        String msg = pjp.getSignature().getName();

        logger.debug(tabs() + "->" + msg +", "+workingThreads.size()+" threads");
        try {
            offset.set(offset.get() + 1);
            return pjp.proceed();
        } finally {
            offset.set(offset.get() - 1);
            logger.debug(tabs() + "<-" + msg + " " + (System.currentTimeMillis() - start) + " ms");
            workingThreads.remove(Thread.currentThread().getName());
        }
    }

    ThreadLocal<Integer> offset = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    String tabs() {
        return "  ".repeat(offset.get());
    }
}
