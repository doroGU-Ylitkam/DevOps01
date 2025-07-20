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
                    // Проверяем, есть ли Dockerfile
                    if (fileExists('Dockerfile')) {
                        echo '🐳 Building Docker image...'
                        docker.build("dockerImage/docImage:latest")
                    } else {
                        error('Dockerfile not found!')
                    }
                }
            }
        }
    }
}
