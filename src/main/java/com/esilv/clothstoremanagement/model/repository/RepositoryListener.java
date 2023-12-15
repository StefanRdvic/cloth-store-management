package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Action;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;

import java.time.LocalDateTime;

/**
 * This interface represents a repository listener
 * see <a href="https://docs.jboss.org/hibernate/orm/6.4/javadocs/org/hibernate/event/spi/package-summary.html">...</a>
 * author: Stefan Radovanovic
 * author: Yannick li
 */
public interface RepositoryListener extends
        PostDeleteEventListener,
        PostInsertEventListener,
        PostUpdateEventListener {

    /**
     * This method is called when an action is performed
     * @param action the action performed
     */
    void onChange(Action action);

    //TODO enhance action with more information

    @Override
    default void onPostDelete(PostDeleteEvent postDeleteEvent) {
        onChange(Action.builder()
                .action("Item deletion")
                .date(LocalDateTime.now())
                .build());
    }

    @Override
    default void onPostInsert(PostInsertEvent postInsertEvent) {
        onChange(Action.builder()
                .action("Item inserted")
                .date(LocalDateTime.now())
                .build());
    }

    @Override
    default void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        onChange(Action.builder()
                .action("Item updated")
                .date(LocalDateTime.now())
                .build());
    }

    @Override
    default boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}
