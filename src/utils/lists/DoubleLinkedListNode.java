package utils.lists;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A doubly linked list node.
 * User: sunside
 * Date: 26.07.11
 * Time: 23:30
 *
 * @param <T> The object type
 */
public class DoubleLinkedListNode<T> {

	/**
	 * The previous node
	 */
	@Nullable
	private DoubleLinkedListNode<T> previousNode;

	/**
	 * The next node
	 */
	@Nullable
	private DoubleLinkedListNode<T> nextNode;

	/**
	 * The payload
	 */
	@Nullable
	private T payload;

	/**
	 * Determines if the element has a follower
	 *
	 * @return <code>true</code> if the element has a follower, otherwise <code>false</code>
	 */
	public boolean hasNext() {
		return nextNode != null;
	}

	/**
	 * Determines if the element has a predecessor
	 *
	 * @return <code>true</code> if the element has a predecessor, otherwise <code>false</code>
	 */
	public boolean hasPrev() {
		return nextNode != null;
	}

	/**
	 * Gets the next node
	 * @return The following node
	 */
	@Nullable
	public DoubleLinkedListNode<T> getNext() {
		return nextNode;
	}

	/**
	 * Gets the previous node
	 *
	 * @return The previous node
	 */
	@Nullable
	public DoubleLinkedListNode<T> getPrev() {
		return previousNode;
	}

	/**
	 * Inserts this node after a given node.
	 *
	 * @param node The node after which to attach this node
	 */
	public void insertAfter(@NotNull DoubleLinkedListNode<T> node) {

		// rewire the right side, if any
		if (node.nextNode != null) {
			this.nextNode = node.nextNode;
			node.nextNode.previousNode = this;
		}

		// rewire the left side
		node.nextNode = this;
		this.previousNode = node;
	}

	/**
	 * Inserts this node before a given node.
	 *
	 * @param node The node after which to attach this node
	 */
	public void insertBefore(@NotNull DoubleLinkedListNode<T> node) {

		// rewire the left side, if any
		if (node.previousNode != null) {
			this.previousNode = node.previousNode;
			node.previousNode.nextNode= this;
		}

		// rewire the right side
		node.previousNode = this;
		this.nextNode = node;
	}

	/**
	 * Removes the node following this one.
	 */
	public void removeAfter() {
		if (nextNode == null) return;

		// Vorwärts-Spaghettiverpointerung
		this.nextNode.previousNode = this.previousNode;
		if(this.previousNode != null) this.previousNode.nextNode = this.nextNode;

		// clear nodes
		this.previousNode = null;
		this.nextNode = null;
	}

	/**
	 * Removes the node preceding this one.
	 */
	public void removeBefore() {
		if (previousNode == null) return;

		// Rückwärts-Spaghettiverpointerung
		this.previousNode.nextNode = this.nextNode;
		if (this.nextNode != null) this.nextNode.previousNode = this.previousNode;

		// clear nodes
		this.previousNode = null;
		this.nextNode = null;
	}

	/**
	 * Gets the payload
	 * @return The payload or <code>null</code> if no payload is set.
	 */
	@Nullable
	public T getPayload() {
		return this.payload;
	}

	/**
	 * Sets the payload
	 * @param payload The payload or <code>null</code> if the payload is to be cleared.
	 */
	public void setPayload(@Nullable T payload) {
		this.payload = payload;
	}

	/**
	 * Determines whether this node has a payload.
	 * @return <code>true</code> if this element has a payload, otherwise <code>false</code>.
	 */
	public boolean hasPayload() {
		return this.payload != null;
	}
}
