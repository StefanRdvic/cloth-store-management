package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.HibernateUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;

/**
 * This class represents a repository provider
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryProvider {

    @Getter(lazy = true)
    @Accessors(fluent = true)
    private static final RepositoryProvider provider = new RepositoryProvider();

    @SuppressWarnings("unchecked")
    public <T> CrudRepository<T> getRepository(Class<T> clazz){
        return (CrudRepository<T>) switch (clazz.getSimpleName()){
            case "Company" -> CompanyRepository.repository();
            case "Cloth" -> ClothRepository.repository();
            case "Accessory" -> AccessoryRepository.repository();
            case "Shoe" -> ShoeRepository.repository();
            default -> throw new IllegalStateException("Unexpected value: " + clazz.getSimpleName());
        };
    }

    /**
     * This method listens to the repository
     * @param listener the listener
     */
    public void listen(RepositoryListener listener){
        EventListenerRegistry eventListenerRegistry = ((SessionFactoryImpl) HibernateUtil.sessionFactory())
                .getServiceRegistry()
                .getService(EventListenerRegistry.class);

        assert eventListenerRegistry != null;

        eventListenerRegistry.appendListeners(EventType.POST_INSERT, listener);
        eventListenerRegistry.appendListeners(EventType.POST_DELETE, listener);
        eventListenerRegistry.appendListeners(EventType.POST_UPDATE, listener);
    }
}
