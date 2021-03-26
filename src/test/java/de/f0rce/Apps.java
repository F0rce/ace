package de.f0rce;

public class Apps {

	private String appname;
	private String version;

	public Apps() {

	}

	public Apps(String appname, String version) {
		super();
		this.appname = appname;
		this.version = version;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
