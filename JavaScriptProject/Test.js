/**
 * 
 */

(function(){
	window.addEventListener("DOMContentLoaded",function(){
		document.getElementById("btnAddTask").addEventListener("click",onBtnAddTaskClick);
		document.getElementById("btnRemoveCompletedTasks").addEventListener("click",onBtnRemoveCompletedTasksClick);
	});
	function onBtnAddTaskClick(){
		var taskName = document.getElementById("txtTaskName").value;
		var ulTaskList = document.getElementById("ulTaskList");
		var newTaskItem = document.createElement("li");
		ulTaskList.innerHTML += newTaskItem;
		newTaskItem.innerHTML = taskName;
		newTaskItem.addEventListener("click",onTaskItemClick);
		ulTaskList.appendChild(newTaskItem);
		document.getElementById("txtTaskName").value = "";
	}
	function onBtnRemoveCompletedTasksClick(){
		console.log("remove completed clicked");
	}
	function onTaskItemClick(){
		this.taskList.add()
	}
})();
