package acsse.csc2a.fmb.pattern;

import acsse.csc2a.fmb.model.FireworkEntity;

public interface AbstractVisitor {
	//if the visitor is FireworkEntity the do this method
	public void visit(FireworkEntity entity);
}
