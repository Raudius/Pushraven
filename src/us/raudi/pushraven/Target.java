package us.raudi.pushraven;

/**
 * Target to send a message to. target can be only one of the TargetType
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#Message.FIELDS-table
 * @author Raudius
 *
 */
public class Target {
	public enum TargetType {
		TOKEN, TOPIC, CONDITION;
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	private TargetType type;
	private String target;
	
	public Target(TargetType type, String target) {
		setTarget(type, target);
	}
	
	public void setTarget(TargetType type, String target) {
		this.type = type;
		this.target = target;
	}
	
	public String getType() {
		return type.toString();
	}
	
	public String getTarget() {
		return target;
	}
}
