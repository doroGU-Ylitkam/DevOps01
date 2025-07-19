pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo '🔨 Building the project with Maven...'
                sh './mvnw clean package'
            }
        }
    }
}
