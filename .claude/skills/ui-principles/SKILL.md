---
name: ui-principles
description: Use when building or reviewing UI layouts, loading states, forms, drag and drop, menus, modals, toasts, progress, carousels, scrolling or any screen people tap and wait on. Applies 19 UX principles (visual stability through toast stacking) with concrete sizes, timings and thresholds, and flags where a design breaks them.
---

# UI principles: 19 more

Use this whenever you build or review an interface people click, drag, type into or wait on. Every principle below has a number or a rule you can apply directly. Pick the ones that fit the screen. Most screens need four or five, not all nineteen.

When you build, apply the rules quietly and mention the important choices in one line each. When you review, list each problem with the principle it breaks, where it happens, and the fix.

## 1. Visual stability

Don't let things jump while people aim. Save the space for anything that loads late (images, banners, ads, embeds) with a fixed width and height, `aspect-ratio` or `min-height`, and drop the content into that slot when it arrives. Never insert content above something the user is about to tap. Animate with `transform` and `opacity`, since changing `top`, `height` or `margin` pushes the layout around. Google counts a Cumulative Layout Shift of 0.1 or less as good and over 0.25 as poor.

## 2. Shared element transition

The thing you tap should grow into the next screen. Move the same element's bounds and corner radius from the card to its spot on the new page, so only one copy of the image exists the whole way. Re-crop the image every frame with `object-fit: cover` so it never stretches. A full-screen zoom like this can run 400 to 500ms with a strong ease-out such as `cubic-bezier(0.32, 0.72, 0, 1)`. Closing reverses it back into the same card. Use the View Transitions API (`view-transition-name`), Motion's `layoutId`, or the iOS 18 zoom transition. With `prefers-reduced-motion`, crossfade instead.

## 3. Chunking

Break long numbers into groups. Card numbers go 4 4 4 4 (Amex is 4 6 5), phone numbers follow the local format, and codes go in groups of 3 or 4. Insert the spaces automatically as people type so nothing typed earlier moves, keep the caret where it should be, accept pasted numbers with or without spaces, and strip the spaces before you validate. Use `font-variant-numeric: tabular-nums` so the digits line up.

## 4. Direct manipulation

Whatever you drag stays under your finger. While the pointer is down, move the element by exactly the pointer's change in position every frame, 1 to 1, with no easing, spring or lerp in between. Keep the point you grabbed under the pointer instead of snapping the element's center to it. Use pointer events with `setPointerCapture` and move it with `transform`. Springs and momentum only start after release, from the release velocity. Give every drag a single-tap or keyboard alternative (WCAG 2.2, 2.5.7).

## 5. User control and freedom

Don't ask "are you sure?" for things that can be undone. Do the action right away and show an Undo in a snackbar, with one action and a 4 to 10 second timeout (Material). Keep the real delete pending until the snackbar is gone, so Undo actually works. Save confirm dialogs for things that can't be undone, like deleting an account or sending money, and name the action on the button ("Delete 3 files") instead of "OK". People click through confirmations they see all the time.

## 6. Odometer effect

When a number changes while people are watching, roll the digits so they can see which way it went. Give each digit its own column with a fixed width and `overflow: hidden`, use tabular figures so nothing jitters, and roll only the digits that changed. Roll up when the value rises and down when it falls. Around 300ms per digit with a small spring overshoot, starting from the right-most digit about 30ms apart, reads well. NumberFlow and Motion's AnimateNumber do this. Skip it for tables that refresh constantly, and just swap the number under `prefers-reduced-motion`.

## 7. Menu aim

When the cursor cuts diagonally from a menu row to its submenu, it crosses other rows. Don't swap the submenu while the cursor is moving inside the triangle between its tip and the submenu's near top and bottom corners. If the cursor stops or leaves the triangle, switch after a short delay (Ben Kamens' menu-aim uses 300ms). Floating UI's `safePolygon` handles this for hover menus. On touch there is no hover, so submenus open on tap.

## 8. Skeleton screens

When a whole page is loading, show its shape instead of a spinner. Draw the skeleton from the same sizes as the real layout so nothing moves when content arrives, then fade content into the exact slots over about 160ms with ease-out. Run one shimmer band across all the shapes at once, linear, left to right. Following Nielsen Norman: under about 1 second show nothing, a skeleton for a full page and a spinner for one small module up to about 10 seconds, and a progress bar beyond that. Under `prefers-reduced-motion`, drop the shimmer.

## 9. Elevation

Things you pick up should lift off the page. On grab, scale to about 1.03 to 1.06 (past 1.08 it reads as a zoom), tilt 2 to 4 degrees and grow the shadow to something deep like `0 24px 40px rgba(0,0,0,.6), 0 8px 14px rgba(0,0,0,.4)`, on a spring that lands in about 150ms. Show a gap or placeholder where it will drop. On release, reverse it and ease into the slot over about 160ms. On dark interfaces a shadow barely shows, so also lighten the lifted surface, the way Material's dark theme does.

## 10. Scrim

Dim everything behind a modal so there's only one place to look. Material uses black at 32% opacity. Dark interfaces need more, around 60 to 75%, because a dark page barely changes at 32%. Fade the scrim in with the dialog (about 200ms) and out faster. Clicking the scrim can close a dialog that doesn't hold unsaved work. Lock page scroll behind it. Don't put a scrim behind menus, popovers or toasts, since they don't block the page.

## 11. Shape morphing

The button itself becomes the spinner, then the check. On press, fade the label out, shrink the width to the button's height and the radius to half the height so it becomes a circle (about 200ms ease-in-out), with the spinner inside. When it's done, spring it back to full width with a small overshoot, draw the check in with `stroke-dashoffset` over about 180ms ease-out, and bring the new label in. Keep the center fixed the whole time. While it's busy, block double submits and set `aria-busy`. If the work finishes in under about 1 second, skip the spinner and go straight to the check.

## 12. Illusion of completeness

If a row ends exactly at the screen edge, people think that's all there is. Size the items so the last one is cut off, with about half of it showing (3.5 cards across instead of 3). Don't add a fade over it, the cut edge is the cue. The same goes for vertical pages: if a section ends right at the fold, people stop scrolling. Snap rows to item starts with `scroll-snap-type: x mandatory` and `scroll-snap-align: start`.

## 13. Inline validation

Tick each rule off as people type instead of waiting for submit. Show the rules up front (8+ characters, a number, a symbol) and mark each one the moment it's met, with a small spring pop (0.6 to 1.15 to 1 over about 260ms). Never show red while someone is still typing in a field for the first time. Check for errors when they leave the field, then check live so the error clears as soon as it's fixed. Show messages in space you already saved so nothing shifts, never clear what they typed, and tie the message to the field with `aria-describedby`.

## 14. Inertial scrolling

Flick something and it should keep gliding, then slow to a stop. On release, keep the release velocity (measured over the last 50 to 100ms of pointer movement) and decay it exponentially, fastest at the moment of release, with no ease-in. Apple's scroll views lose velocity at 0.998 per ms (normal) or 0.99 per ms (fast). To find where it will land, Apple's formula is `velocity / 1000 * rate / (1 - rate)`, with velocity in points per second. Motion's inertia defaults are power 0.8 and timeConstant 700. Native scrolling already does this, so only build it for custom surfaces like maps, canvases and carousels, and add rubber banding at the edges.

## 15. Change blindness

People miss changes that happen away from where they're looking, like a cart count in the corner. Carry the eye there: fly a small copy of the item from the button to the cart (about 400ms, speeding up as it goes, shrinking to about 0.4), then give the target one pop (1 to 1.16 and back over about 200ms). One pop is enough. If you can't move the eye, put the feedback right where they tapped. Announce the change with an `aria-live` region for screen readers.

## 16. Progressive disclosure

Show what most people need and tuck the rest behind "Advanced". Pick the few settings most people change (3 basics up front works well) and put the rest behind one row that says what's in it and how many, like "Advanced · 11 more". Defaults should be right for most people. Keep it to two levels, since Nielsen Norman found designs that go deeper than two usually get people lost. Never hide something people use every time.

## 17. Common fate

Things that move together look like they belong together. When someone drags a selection of three items, move all three on the same path at the same speed on the same frame, keeping their layout, with no stagger and no lag between them. Anything that isn't part of the group should stay still. Stagger is for lists coming in, never for things the user is moving as one.

## 18. Visibility of system status

If something takes a while, show how far along it is. Nielsen's limits: 0.1 seconds feels instant, 1 second keeps people's flow, and 10 seconds is about as long as they'll stay focused. A spinner is fine for a few seconds. Past about 10 seconds, show a progress bar with the percent, the amount done and an estimate ("about 5s left"), and let people cancel. Round the estimate and update the text in steps, not every frame. Progress should never move backwards, and progress that speeds up near the end feels shorter (Harrison et al., 2007).

## 19. Toast stacking

When toasts pile up, stack them behind the newest so they don't cover the page. Follow Sonner's defaults: at most 3 visible, the newest in front, each older one moved up 14px and scaled down 5% per step, with its text hidden. Expand the stack on hover with 14px gaps and pause the timers while it's open. Toasts last about 4 seconds, are 356px wide, and use an `aria-live="polite"` region. Never place them over the main action.

## Quick checks before you ship

- Does anything move or jump after the page first loads?
- Does every drag stay 1 to 1 under the pointer, and does every drag have a tap or keyboard alternative?
- Can people undo instead of confirming, and does every dialog name its action?
- Does every wait longer than about 10 seconds show real progress?
- Are form errors shown when people leave a field, never while they're typing?
- Does every change people need to notice happen where they're looking?
- Does it still work with `prefers-reduced-motion` turned on?