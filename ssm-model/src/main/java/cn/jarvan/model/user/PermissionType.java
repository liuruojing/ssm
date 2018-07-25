package cn.jarvan.model.user;

public class PermissionType {
    private Long permissontypeId;

    private String permissiontypeName;

    public Long getPermissontypeId() {
        return permissontypeId;
    }

    public void setPermissontypeId(Long permissontypeId) {
        this.permissontypeId = permissontypeId;
    }

    public String getPermissiontypeName() {
        return permissiontypeName;
    }

    public void setPermissiontypeName(String permissiontypeName) {
        this.permissiontypeName = permissiontypeName == null ? null : permissiontypeName.trim();
    }
}