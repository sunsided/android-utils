package utils.lists;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A singly linked list node.
 * User: sunside
 * Date: 26.07.11
 * Time: 23:30
 *
 * @param <T> The object type
 */
public class SingleLinkedListNode<T> {

	/**
	 * The next node
	 */
	@Nullable
	private SingleLinkedListNode<T> nextNode;

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
	 * Gets the next node
	 *
	 * @return The following node
	 */
	@Nullable
	public SingleLinkedListNode<T> getNext() {
		return nextNode;
	}

	/**
	 * Inserts this node after a given node.
	 *
	 * @param node The node after which to attach this node
	 */
	public void insertAfter(@NotNull SingleLinkedListNode<T> node) {

		// rewire the right side, if any
		if (node.nextNode != null) {
			this.nextNode = node.nextNode;
		}

		// rewire the left side
		node.nextNode = this;
	}

	/**
	 * Removes the node following this one.
	 */
	public void removeAfter() {
		if (nextNode == null) return;

		// Vorwärts-Spaghettiverpointerung
		this.nextNode = this.nextNode.nextNode;
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
