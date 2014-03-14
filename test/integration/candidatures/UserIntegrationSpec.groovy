package candidatures

import grails.test.spock.IntegrationSpec
import spock.util.mop.Use

/**
 *
 */
class UserIntegrationSpec extends IntegrationSpec {
    void "Saving our first user"() {
        given: 'A brand new user'
        def joe = new User(username: 'joe', password: 'secret')

        when: 'the user is saved'
        joe.save()

        then: 'it is successfully saved in database and can be accessed easily'
        joe.errors.errorCount == 0
        joe.id != null
        User.get(joe.id).username == joe.username
    }

    def "updating a user"() {
        given: 'an existing user'
        def existingUser = new User(username: 'joe', password: 'secret').save(failOnError: true)

        when: 'A property is changed'
        def foundUser = User.get(existingUser.id)
        foundUser.password = 'sesame'
        foundUser.save(failOnError: true)

        then: 'The change is reflected in database'
        User.get(existingUser.id).password == 'sesame'
    }

    def 'deleting a user'() {
        given: 'An existing user'
        def existingUser = new User(username: 'joe', password: 'secret').save(failOnError: true)

        when: 'it is deleted'
        def foundUser = User.get(existingUser.id)
        foundUser.delete(flush: true)

        then: 'the user is not present anymore in database'
        !User.exists(foundUser.id)
    }
}
