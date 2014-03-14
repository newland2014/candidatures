package candidatures

class User {
    String username
    String password
    Date dateCreated

    static constraints = {
        username size: 3..20, unique: true
        password size: 8..16
    }
}
