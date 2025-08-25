def call(Map args = [:]) {
    withCredentials([usernamePassword(credentialsId: 'docker-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh "echo \$PASS | docker login -u \$USER --password-stdin docker.io"
    }
}
