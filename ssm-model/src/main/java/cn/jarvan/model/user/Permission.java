package cn.jarvan.model.user;

public class Permission {
    private Long permissionId;

    private String permissionName;

    private String perimissionUrl;

    private Long permissioonType;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPerimissionUrl() {
        return perimissionUrl;
    }

    public void setPerimissionUrl(String perimissionUrl) {
        this.perimissionUrl = perimissionUrl == null ? null : perimissionUrl.trim();
    }

    public Long getPermissioonType() {
        return permissioonType;
    }

    public void setPermissioonType(Long permissioonType) {
        this.permissioonType = permissioonType;
    }
}