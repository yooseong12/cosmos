package com.toy.cosmos.domain.value;

import java.util.Arrays;
import java.util.List;

public enum Authority {
    ROLE_ANONYMOUS,
    ROLE_USER() {
        @Override
        public List<Permission> getPermissions() {
            return List.of();
        }

        @Override
        public boolean checkPermission(Permission permission) {
            return getPermissions().contains(permission);
        }
    },

    ROLE_ADMIN() {
        @Override
        public List<Permission> getPermissions() {
            return Arrays.asList(Permission.COUPON, Permission.POINT);
        }

        @Override
        public boolean checkPermission(Permission permission) {
            return getPermissions().contains(permission);
        }
    },
    ROLE_DELIVERY_MANAGER,
    ROLE_DEFERRED_PAYMENT;

    public List<Permission> getPermissions() {
        throw new AbstractMethodError();
    }

    public boolean checkPermission(Permission permission) {
        throw new AbstractMethodError();
    }
}
