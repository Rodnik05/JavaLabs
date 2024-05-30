rootProject.name = "JavaLabs"
include("CatApp")
include("CatApp:DAO")
findProject(":CatApp:DAO")?.name = "DAO"
include("CatApp:Controller")
findProject(":CatApp:Controller")?.name = "Controller"
include("CatApp:Service")
findProject(":CatApp:Service")?.name = "Service"
