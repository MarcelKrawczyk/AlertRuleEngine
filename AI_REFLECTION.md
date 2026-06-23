# AI Usage Reflection

## 1. Which AI tools did you use?

I used Claude (Anthropic) as a conversational assistant, but only for part of the
task. I completed Stages 1, 2, and 4 on my own. I used the assistant mainly during
Stage 3, to understand the design trade-offs behind the extensible rule structure
(specifically why an `AlertRule` interface is preferable to tying the engine to a
concrete rule class).

## 2. One interaction that helped me

**Prompt I gave:**

```
I have a working rule engine where `RuleEngine` holds a `List<DivisibilityRule>`
and iterates over the rules, concatenating their labels. It works correctly for
the values 1–20. I'm wondering whether its potential extensibility could be
optimized further and made easier to work with in the future — do you have any
ideas on what I should improve, and where the weak points of my implementation
are?
```

**Summary of the response:**

The assistant pointed out that my main weakness was that `RuleEngine` held a
`List<DivisibilityRule>`, which tied the engine to one concrete rule type. It
explained that this works only as long as every rule is about divisibility, but
the moment a different kind of rule appears (for example "value > 100 -> CRITICAL"),
that rule would not fit the list and I would have to modify the engine itself -
which is exactly what the extensibility requirement is meant to avoid. The
suggested fix was to introduce an `AlertRule` interface with a single
`evaluate(int)` method, so the engine could hold `List<AlertRule>` - any object
that knows how to evaluate, regardless of its concrete type.

**What I took from it and why it was useful:**

My solution already worked, but I didn't really get why it wasn't "extensible
enough" until I saw the example with a rule that isn't about divisibility, like
"value > 100 -> CRITICAL". That's when it clicked: my engine only accepted
`DivisibilityRule`, so any other kind of rule would force me to go back and change
the engine itself. With an `AlertRule` interface, the engine just takes "anything
that can be evaluated", so adding a new kind of rule means writing a new class and
leaving the existing code alone. I went with that. I pulled out the `AlertRule`
interface and made `RuleEngine` depend on it instead of on `DivisibilityRule`..

## 3. One AI suggestion I modified or rejected

**What the AI suggested:**

Alongside the interface, the assistant suggested making a defensive copy of the
rules list in the `RuleEngine` constructor `this.rules = List.copyOf(rules)`, so
the engine would be immune to the list being modified from the outside after it
was passed in.

**Why I didn't use it as-is:**

In this project the rule list is created once, inside `run()`, using `List.of()`
which already returns an unmodifiable list, and it is never shared
from anywhere else. Adding `List.copyOf` would guard against a scenario that does
not exist here, so it would be an extra noise in which would affect code clarity
the code at this stage.

**What I did instead:**

I kept the simple reference assignment and treated the defensive copy as an
intentional "later" decision: if the rule list ever came from an external source or
were built dynamically, the copy would become justified. So I rejected it as
premature rather than as something I misunderstood.