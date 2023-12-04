package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Action;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;

import java.time.LocalDateTime;

public interface RepositoryListener extends
        PostDeleteEventListener,
        PostInsertEventListener,
        PostUpdateEventListener {

    void onChange(Action action);

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
