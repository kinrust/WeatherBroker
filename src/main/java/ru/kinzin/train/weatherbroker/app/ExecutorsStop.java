package ru.kinzin.train.weatherbroker.app;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ExecutorsStop implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(final ContextClosedEvent event) {

        HibernateUtil.shutdown();
    }
}
