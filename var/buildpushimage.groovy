def call(Map config = [:]) {
    def image = config.image ?: 'default-image'
    def tag = config.tag ?: 'latest'
    def onlyPush = config.onlyPush ?: false
    
    if (!onlyPush) {
        sh "docker build -t ${image}:${tag} ."
    }
    
    if (!config.onlyBuild) {
        withCredentials([usernamePassword(
            credentialsId: 'docker-credentials',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )]) {
            sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
            sh "docker push ${image}:${tag}"
        }
    }
}
