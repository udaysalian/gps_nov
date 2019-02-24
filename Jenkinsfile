#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        sh "java -version"
    }

    stage('clean') {
        sh "chmod +x gradlew"
        sh "./gradlew clean --no-daemon"
    }

    stage('npm install') {
        sh "./gradlew npmInstall -PnodeInstall --no-daemon"
    }

    stage('backend tests') {
        try {
            sh "./gradlew test -PnodeInstall --no-daemon"
        } catch(err) {
            throw err
        } finally {
            junit '**/build/**/TEST-*.xml'
        }
    }

    stage('frontend tests') {
        try {
            sh "./gradlew npm_test -PnodeInstall --no-daemon"
        } catch(err) {
            throw err
        } finally {
            junit '**/build/test-results/jest/TESTS-*.xml'
        }
    }

    stage('protractor tests') {
        sh '''./gradlew &
        bootPid=$!
        sleep 60s
        yarn e2e
        kill $bootPid
        '''
    }
    stage('packaging') {
        sh "./gradlew boo        sh "./gradlew bootWar -x test -Pprod -PnodeInstall --no-daemon"
        archiveArtifacts artifacts: '**/build/libs/*.war', fingerprint: truetest -Pprod -PnodeInstall --no-daemon"
        archiveArtifacts         sh "./gradlew bootWar -x test -Pprod -PnodeInstall --no-daemon"
        archiveArtifacts artifacts: '**/build/libs/*.war', fingerprint: trues: '**/build/libs/*.war', fingerprint: true
    }

    stage('deployment') {        sh "./gradlew bootWar -x test -Pprod -PnodeInstall --no-daemon"
        archiveArtifacts artifacts: '**/build/libs/*.war', fingerprint: true
        sh "./gradlew deployHeroku --no-daemon"
    }

    stage('quality analysis') {
        withSonarQubeEnv('sonar') {
            sh "./gradlew sonarqube --no-daemon"
        }
    }

    
}
