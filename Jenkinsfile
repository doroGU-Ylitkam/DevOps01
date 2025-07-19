pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo '🧪 Running tests with Maven...'
                sh 'chmod +x mvnw'
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Building JAR artifact...'
                sh './mvnw clean package -DskipTests'  // Сборка без повторного запуска тестов
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh '''
                        echo "Installing Docker inside Jenkins container..."
                        apt-get update && apt-get install -y docker.io
                        docker build -t my-app:${BUILD_ID} .
                    '''
                }
            }
        }
    }
}
