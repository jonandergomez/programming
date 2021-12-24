
ListIntLinked compress(ListIntLinked origin)
{
  ListIntLinked target = new ListIntLinked();

  for (boolean valid = origin.begin(); valid; valid=origin.next()) {
    int value = origin.get();
    valid = origin.next();
    if (valid) {
      value = 2 * value + origin.get();
      target.append(value);
    } else {
      target.append(value - 2);
    }
  }
  return target;
}

void removeGreaterThan(int e)
{
  cursor = first;

  while (cursor != null) {
    if (cursor.getValue() > e) {
      --size;
      if (size == 1) {
        first = last = cursor = null;
      } else if (cursor == first) {
        first = cursor = cursor.getNext();
        first.setPrevious(null);
      } else if (cursor == last) {
        last = last.getPrevious();
        last.setNext(null);
        cursor = null;
      } else {
        NodeInt previous = cursor.getPrevious();
        NodeInt next = cursor.getNext();

        previous.setNext(next);
        next.setPrevious(previous);

        cursor = next;
      }
    } else {
      cursor = cursor.getNext();
    }
  }
  cursor = first;
}

public static void moveBack(QueueIntLinked q, int x)
{
  boolean extracted = false;
  for (int i = 0; i < q.size(); i++) {
    int value = q.pop();
    if (value == x && !extracted) {
      extracted = true;
    } else {
      q.push(value);
    }
  }
  if (extracted) q.push(x);
}


NodeChar string2sequence(String s)
{
  if (s == null || s.length() == 0) return null;

  NodeChar first = new NodeChar(s.charAt(0));
  NodeChar node = first;

  for (int i = 1; i < s.length(); i++) {
    node.setNext(new NodeChar(s.charAt(i)));
    node.getNext().setPrevious(node); // I am your previous
    node=node.getNext();
  }
  return first;
}

void append(int x)
  throws NoSuchElementException
{
  if (cursor == null) throw new NoSuchElementException("Cursor at end");

  NodeInt node = new NodeInt(cursor, x, cursor.getNext());
  cursor.setNext(node);
  if (cursor != last) {
    node.getNext().setPrevious(node); // I am your previous
  } else {
    last = node;
  }
  cursor = node;
}

QueueIntLinked merge(QueueIntLinked q1, QueueIntLinked q2)
{
  QueueIntLinked merged = new QueueIntLinked();

  int n = Math.min(q1.size(), q2.size());
  int v;

  for (int i = 0; i < n; i++ ) {
    v = q1.pop(); merged.push(v); q1.push(v);
    v = q2.pop(); merged.push(v); q2.push(v);
  }

  for (int i = n; i < q1.size(); i++) {
    v = q1.pop(); merged.push(v); q1.push(v);
  }

  for (int i = n; i < q2.size(); i++) {
    v = q2.pop(); merged.push(v); q2.push(v);
  }

  return merged;
}
