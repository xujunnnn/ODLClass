/*
 * Copyright (c) 2015 NEC Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package NetMonitor;

public class Pair<T,O> {
	private T left;
	private O right;
	public T getLeft() {
		return left;
	}
	public void setLeft(T left) {
		this.left = left;
	}
	public O getRight() {
		return right;
	}
	public void setRight(O right) {
		this.right = right;
	}
	public Pair(){
		left=null;
		right=null;
	}
	public Pair(T left,O right){
		this.left=left;
		this.right=right;
		
	}

}
