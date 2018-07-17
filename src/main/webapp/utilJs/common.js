function isBlank(str) {
	if (str==undefined  || str==null ||str=='' || str.trim().length==0) {
		return true;
	} else {
		return false;
	}
}