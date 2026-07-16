## Headers Exchange
- Published message has no routing key
- Has headers instead (key-value pairs)
- The exchange will route the message to the queue based on the headers
- The headers can be matched exactly (all k-v) or by a single match (any k-v)

___

e.g. The following queue header will require all the headers to match:

```typescript
const headers = {
    "x-match": "all",
    "mobile": "samsung",
    "tv": "sony"
}
```

The header from a published message therefore must be:

```typescript
const headers = {
    "mobile": "samsung",
    "tv": "sony"
}
```

e.g. The following queue header will require any of the headers to match:

```typescript
const headers = {
    "x-match": "any",
    "mobile": "samsung",
    "tv": "sony"
}
```

The header from a published message therefore must be similar to:

```typescript
const headers = {
    "mobile": "samsung",
    "tv": "LG"
}
```

In my exchange:

```typescript
const headers_AC = {
    "x-match": "any",
    "item": "ac",
    "quantity": 1
}

const headers_TV = {
    "x-match": "all",
    "item": "ac",
    "quantity": 2
}

const headers_MOBILE = {
    "x-match": "any",
    "item": "mobile",
    "quantity": 1
}
```