def deployStage() {
    stage('Deploy prometheus to host') {
        dir(serviceDir) {
            withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                withEnv(["PATH=${env.HOME}/bin:${env.PATH}"]) {
                    echo "KUBECONFIG path is: ${env.KUBECONFIG}"
                    sh "kubectl get nodes --kubeconfig ${env.KUBECONFIG}"

                    sh "pwd"
                    
                    echo "Deploying to Kubernetes using Helm..."
                    sh "helm upgrade --install prometheus-release ${serviceDir}/Prometheus-Helm"

                    sh "helm upgrade --install pushgateway-release ${serviceDir}/Pushgateway"
                }
            }
        }
    }
    echo "✅ Deploy complete"
}
return this