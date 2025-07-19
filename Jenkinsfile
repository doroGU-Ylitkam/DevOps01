pipeline {
    agent any

    stages {
        stage('Prepare') {
            steps {
                sh 'chmod +x mvnw'  // Даём права на выполнение
            }
        }
        stage('Build') {
            steps {
                echo '🔨 Building the project with Maven...'
                sh './mvnw clean package'
            }
        }
    }
}
