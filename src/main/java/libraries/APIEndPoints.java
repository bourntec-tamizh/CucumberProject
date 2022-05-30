package libraries;

public enum APIEndPoints {

    userApi("/api/users"),
    loginApi("/api/login"),
    registerApi("/api/register")
    ;

    private  final String resource;

    APIEndPoints(String resource)
    {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

}
