def checkStage() {
    stage('Check services in kubernetes') {
        withEnv(["PATH=${env.HOME}/bin:${env.PATH}"]) {
            withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                echo "Check service in kubernetes"

                def checkDeploy = sh (
                    script: "kubectl get deploy",
                    returnStdout: true
                ).trim()
                if(!checkDeploy.contains("prometheus-deployment") &&!checkDeploy.contains("pushgateway")){
                    error("Services isn't in kuber")
                }
            }
        }

    }
    echo "✅ Check complete"
}
return this