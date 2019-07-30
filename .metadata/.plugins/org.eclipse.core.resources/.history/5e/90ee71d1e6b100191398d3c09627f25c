package com.spring.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBizUser is a Querydsl query type for BizUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBizUser extends EntityPathBase<BizUser> {

    private static final long serialVersionUID = 2115300805L;

    public static final QBizUser bizUser = new QBizUser("bizUser");

    public final BooleanPath accountNonExpired = createBoolean("accountNonExpired");

    public final BooleanPath accountNonLocked = createBoolean("accountNonLocked");

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    public final BooleanPath credentialsNonExpired = createBoolean("credentialsNonExpired");

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public final ListPath<BizRole, QBizRole> roleList = this.<BizRole, QBizRole>createList("roleList", BizRole.class, QBizRole.class, PathInits.DIRECT2);

    public final StringPath salt = createString("salt");

    public final DateTimePath<java.util.Date> updated = createDateTime("updated", java.util.Date.class);

    public final StringPath username = createString("username");

    public QBizUser(String variable) {
        super(BizUser.class, forVariable(variable));
    }

    public QBizUser(Path<? extends BizUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBizUser(PathMetadata metadata) {
        super(BizUser.class, metadata);
    }

}

