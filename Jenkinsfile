@Library('pipelib@master') _

def THREADFIX_ID = env.THREADFIX_ID ? env.THREADFIX_ID : '115'

node {
  def mvn = tool 'M3'
  def root = pwd()

  stage('Setup') {
    git([
      url: env.GIT_URL ? env.GIT_URL : 'http://github.com/venicegeo/dg-pzsvc-hello-java',
      branch: "master"
    ])
  }

  stage('Archive') {
    sh """
      ${mvn}/bin/mvn clean package -U -Dmaven.repo.local=${root}
      cp target/dg-pzsvc-hello-java-1.0.0.jar ${root}/dg-pzsvc-hello-java.jar
    """
    // Removing mavenpush until we know where we are archiving to.
    //mavenPush()
  }

  stage('CI Deploy') {
    sh """
      cp target/dg-pzsvc-hello-java-1.0.0.jar ${root}/dg-pzsvc-hello-java.jar
    """
    cfPush { 
    	ldapCredentialID=pcf_user 
    }
    zap {
      threadfixId = THREADFIX_ID
    }
    cfBgDeploy()
  }

  stage('Integration Testing') {
    postman()
  }

  stage('Reset') {
    git([
      url: env.GIT_URL ? env.GIT_URL : 'https://github.com/venicegeo/dg-pzsvc-hello-java',
      branch: "master"
    ])
  }

  stage('Staging Deploy') {
    cfPush {
      cfDomain  = 'TBD'
      cfSpace   = 'stage'
    }
    cfBgDeploy {
      cfDomain  = 'TBD'
      cfSpace   = 'stage'
    }
  }

  stage('Cleanup') {
    deleteDir()
  }
}
