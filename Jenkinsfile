pipeline {
    agent none  // Отключаем глобальный агент

    stages {
        // Этапы Test и Build остаются без изменений
        stage('Test') {
            agent any  // Запускаем на любом агенте
            steps {
                echo '🧪 Running tests...'
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build') {
            agent any
            steps {
                echo '🔨 Building JAR...'
                sh './mvnw clean package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        // Новый этап: сборка Docker-образа через DinD
        stage('Docker Build') {
            agent {
                docker {
                    image 'docker:dind'  // Используем DinD-контейнер
                    args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                echo '🐳 Building Docker image...'
                script {
                    // Собираем образ (Dockerfile должен быть в корне проекта)
                    sh 'docker build -t my-app:latest .'
                }
            }
        }
    }
}
