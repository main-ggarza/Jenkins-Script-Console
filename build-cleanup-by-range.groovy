
/////////////////////////////////////////
///
///  This will delete the builds from jobName
///  from firstJobNumber to lastJobNumber
///
///  Description:
///  Deletes builds within a specified range (from
///  firstJobNumber to lastJobNumber) for a given job.
///
/////////////////////////////////////////



def jobName = 'Sandbox/test_gg'
def firstJobNumber = 60
def lastJobNumber = 69
def job = Jenkins.instance.getItemByFullName(jobName)

if (job != null) {
    for (int x = firstJobNumber; x <= lastJobNumber; x++) {
        def build = job.getBuildByNumber(x)

        if (build != null) {
            println "Starting to delete build #${x} from ${jobName}..."

            build.delete()

            println "Build #${x} has been deleted successfully."
        } else {
            println "Build #${x} in ${jobName} does not exist. Please check firstJobNumber and lastJobNumber values."
        }
    }
    println "All builds from #${firstJobNumber} to #${lastJobNumber} have been processed for job ${jobName}."
} else {
    println "Job '${jobName}' does not exist. Please check the job name."
}
