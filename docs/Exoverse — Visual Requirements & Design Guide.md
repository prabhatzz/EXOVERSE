# Exoverse — Visual Requirements & Design Guide v2.0


## Changes from v1

v1 was written for the project's earlier name, RabbitHole. v2 keeps every approved visual decision, renames the product to **Exoverse** ("Worlds beyond our own"), and adds the experiences that the new scope requires. Sections 1 to 11 below are v1 content, kept in the same order and numbering with small additions marked as such. Sections 12 to 16 are new.

| Area | Change |
| --- | --- |
| Name | RabbitHole becomes Exoverse in every label, navigation item and example |
| Homepage | Direction unchanged. The detailed layout, type and interaction spec is Homepage Visual Breakdown v0.2 (plate system, six sections and footer). Section 4 stays as the summary |
| Navigation | Adds Labs and Ask to the minimal top navigation (section 4) |
| Labels | v1's evidence status becomes the five-label system plus a separate research status chip, a model prediction label and an AI-generated mark (section 14) |
| Labs | New visual requirements for the three Labs: Exoplanet Finder, Mars Terrain Lab, Mission Control (section 12) |
| AI assistant | New visual requirements for the topic-page panel, the global Ask page, citations and limit states (section 13) |
| Checklist | Section 11 kept; section 15 adds the new checks |
| Open items | Homepage decision V1 and V4 are decided; V2, V3 and V5 remain open. Section 16 lists what is still open, including questions for the new areas |

The approved homepage direction is not reopened. Labs and the assistant inherit its tokens, type and restraint instead of introducing a new style.

## 1. Core visual concept

The defining visual is a lone astronaut in a vast, dark cosmic scene. The astronaut is a focal storytelling element that communicates scale, curiosity, discovery and the unknown. The site should feel like an immersive expedition, not a conventional SaaS dashboard.

Design keywords: cinematic, mysterious, premium, minimal, atmospheric, exploratory, visual-first, calm, futuristic, scientifically curious.

The astronaut should feel integrated into the scene, not pasted onto a generic gradient. Use a large cinematic composition with deep negative space for readable headline and navigation.

## 2. Art direction

- **Background:** near-black or navy space, subtle star field, restrained nebula haze and distant planetary light. Avoid noisy particle overload.
- **Hero:** a single astronaut seen from behind or in three-quarter view, looking toward a planet, luminous horizon, distant galaxy or cosmic phenomenon. Keep the figure visually strong but leave clean text space. (Decision V1, settled in Homepage Visual Breakdown v0.2: a custom 3D composition rendered offline from licensed assets, shipped as optimized layered images, with the astronaut seen from behind or three-quarter. Type, navigation, buttons and annotations stay live frontend elements.)
- **Lighting:** controlled rim light and soft atmospheric glow. Use cyan or ice-blue highlights with occasional violet; avoid excessive neon.
- **Imagery:** prioritize credible NASA or ESA imagery where licensed and properly attributed, plus purpose-built 3D visualizations and clearly labeled artist impressions.
- **Cards:** minimal dark translucent surfaces or subtle bordered panels; imagery should lead, text should support.
- **Typography:** modern sans-serif with strong display headline and highly legible body text. Use restrained letter spacing for small uppercase labels. The proposed families (Inter, Instrument Serif, IBM Plex Mono) are in the Homepage Visual Breakdown.
- **Motion:** slow parallax, gentle star drift, subtle light movement and deliberate transitions. Motion must never make reading or navigation difficult.

## 3. Design tokens

| Token | Value | Use |
| --- | --- | --- |
| void | #050812 | Page background, hero base |
| abyss | #070B16 | Alternate section background |
| surface | #0D1422 | Cards, panels, image frames |
| surface-raised | #111C2D | Hover, modal, menus |
| starlight | #F4F7FB | Headlines, primary text |
| mist | #A7B4C7 | Body and secondary text |
| dim | #718096 | Captions and annotations (not body copy) |
| hairline | rgba(160,190,220,0.16) | Borders, rules, grid lines (grid lines drawn fainter, about 6%, on the hero and premise) |
| ice (primary accent) | #70D6E8 | Links, focus ring, selected states, key measured values, headline fade end, planet rim light |
| violet (secondary accent) | #9B8CFF | Secondary emphasis only, such as a comparison series; never a large fill |
| evidence green | restrained green, value fixed in the first token file | Observed and Established status only |
| amber | restrained amber, value fixed in the first token file | Caution notes and Hypothesis or Speculative labels only |

These are starting tokens, not fixed branding. Verify contrast in implementation and never use color alone to communicate scientific status. This table is the single token system for Home, Explore, Topic pages, Experiences, Labs and the assistant, and it is identical to the table in Homepage Visual Breakdown v0.2. Ice is the only accent used for interaction; violet and amber are never decorative. The ember accent proposed in Breakdown v0.1 is dropped from the token set (decision V5, unapproved).

## 4. Home page visual requirements

- **Top navigation:** Exoverse wordmark; Explore; Experiences; compact search. Release 1 shows only these. My Universe and account controls appear when accounts launch (M9), Labs when the first Lab is published (M12), and Ask when the global Ask page launches (M17). No coming-soon, empty or disabled links. Keep it calm and low-profile; later links are plain text like the others, with no badges or highlight.
- **Hero:** full-width or near-full-screen astronaut scene; concise headline (the v1 example was "Beyond what we know."; final copy follows the Homepage Breakdown); one-sentence supporting copy; primary CTA "Start exploring"; secondary CTA "Explore the unknown".
- **Search:** prominent, elegant search field with examples such as "habitable exoplanets", "black holes" and "wormholes".
- **Featured discovery:** one large editorial card with a striking image, a short hook, evidence label and Explore action.
- **Explore collections:** 4 to 6 visual cards, not a dense dashboard. Prioritize Exoplanets & Life, Black Holes, Cosmic Mysteries, Multiverse & Physics, Future Space Travel.
- **Featured experience:** one cinematic wide card for an interactive journey, for example "Inside a black hole" or "Design a generation ship".
- **Labs and assistant on the homepage:** neither appears in the first release of the homepage. When Labs launch (Release 2), they appear as one quiet line or card inside the Experiences section, not a new section and not in the hero. The assistant lives on topic pages from M11 and behind the Ask link from M17.
- **Footer:** sources and about, scientific standards, attribution and basic links.

## 5. Explore and topic detail pages

### Explore page

- Large search and concise category filters.
- Visual cards with topic title, one-line hook, category and evidence label.
- Allow progressive filtering and pagination or infinite loading; do not render the entire universe at once.
- Keep cards image-led with consistent aspect ratios and readable overlays.

### Reusable topic page

- Hero visual with topic name, category, evidence label and a concise one- or two-sentence explanation. (Added: a research status chip may sit beside the evidence label; see section 14.)
- Key facts presented as a small set of scannable values, including units and uncertainty where relevant. (Added: each value carries a small marker for how it was obtained, such as measured, derived, estimated, theoretical or model prediction.)
- Primary visual or 3D experience with clear controls and a static fallback.
- Sections: What we know; What remains unknown; How it works; Discovery and history; Related topics; Sources.
- Related topics appear as a small set of connected cards or nodes, with an explicit "Explore more connections" action.
- Long-form scientific detail is optional and progressively disclosed.
- (Added) An "Ask about this topic" control opens the assistant panel (section 13). It is a quiet text button near the section navigation, not a floating chat bubble.
- (Added) Where a Lab has a result for this topic, one understated link to it appears under Related topics. It never sits in the hero.

## 6. Experiences and spacecraft visual requirements

- The Experiences landing page should feel like a curated mission gallery, not a tool dashboard.
- Each experience has a cinematic cover, clear title, short premise, runtime or interaction hint and scientific-status label.
- 3D viewer controls: drag to rotate, scroll or pinch to zoom, reset view, optional labels and hotspots, and accessible keyboard equivalents.
- Spacecraft Vault: distinguish existing spacecraft, researched concepts, proposed designs and speculative vehicles.
- Asterion concept: present it as a fictional speculative generation-ship concept inspired by theoretical ideas; do not imply a real wormhole drive exists.
- Wormhole and multiverse scenes must visibly state that they are theoretical or hypothetical visualizations.
- Keep UI chrome minimal while the 3D scene is active; provide a clear exit or back control and a concise information panel.
- (Added) The Labs landing page (section 12) follows the same mission-gallery pattern as Experiences and is reached from the Labs link, not from inside the Experiences list.

## 7. Motion and interaction principles

- Use motion to reveal depth or guide attention, not as decoration everywhere.
- Prefer 200 to 500 ms UI transitions; reserve slower movement for atmospheric background scenes.
- Support prefers-reduced-motion and provide a static experience where appropriate.
- Pause animation and render loops when the experience is off-screen or the tab is hidden.
- Lazy-load 3D bundles and models only after the user opens an experience.
- Provide visible focus states, hover states, pressed states and loading indicators.
- (Added) Charts in Labs animate in at most once, briefly, and never loop. The assistant's answer appears as a complete block after loading, with no typewriter effect.

## 8. Responsive behavior

- Desktop: full cinematic hero and richer 3D controls.
- Tablet: retain strong visual hierarchy; simplify card grids and side panels.
- Mobile: shorter hero crop, concise copy, touch-friendly controls and stacked cards.
- Low-powered or mobile devices: lower model detail, reduce particle counts and offer static or 2D fallbacks.
- Do not make essential facts or navigation dependent on hover.
- (Added) On mobile the assistant panel becomes a bottom sheet, Lab charts become full-width with horizontal scroll and a text summary above them, and image comparison sliders keep a visible drag handle with a tap-to-toggle alternative.

## 9. Accessibility and content integrity

- Meet WCAG-informed contrast and keyboard navigation basics.
- Provide alt text and captions for meaningful imagery; mark decorative visuals appropriately.
- Do not encode evidence status using color alone; pair color with text and an icon label.
- Label images as actual observation, scientific visualization, artist impression or hypothetical simulation.
- Show source attribution and uncertainty for scientific values.
- Do not describe a habitable-zone planet as confirmed habitable or imply extraterrestrial life has been detected.
- (Added) Every chart has a text summary and a data table alternative; segmentation classes are distinguished by label and pattern as well as color.
- (Added) Assistant answers are read out as a single region when they arrive, citations are real links reachable by keyboard, and limit or error messages are announced.
- (Added) A model prediction is never described as a discovery, a confirmation or a detection.

## 10. Avoid these design mistakes

- Generic admin dashboard layout with permanent sidebar and too many widgets.
- Too many cards, categories, metrics or paragraphs on the home page.
- Bright neon gradients everywhere, excessive glow, random particles or unreadable thin text.
- Generic AI space wallpaper used as if it were a real astronomical image.
- Autoplay audio, aggressive scroll hijacking or motion that blocks reading.
- Overloaded 3D scenes on initial page load.
- Presenting speculative wormholes, warp drives or parallel universes as established science.
- (Added) Labs that look like a data-science notebook or analytics dashboard, with dense metric grids and leaderboards.
- (Added) A chatbot look for the assistant: avatars, personalities, floating bubbles, emoji, or text that implies the assistant is a person or an authority.
- (Added) Showing a model's score as a probability of life, habitability or confirmation.

## 11. Visual acceptance checklist (v1, unchanged)

- The astronaut-led dark hero is the unmistakable visual signature.
- The first screen communicates what Exoverse is and how to start exploring.
- Navigation is simple and the page does not resemble a CRM dashboard.
- Content is image-led, concise and progressively disclosed.
- Cards, labels, typography and spacing are consistent across pages.
- 3D experiences are optional, lazy-loaded and have fallbacks.
- Mobile, reduced-motion, keyboard and contrast behavior are checked.
- Scientific status and image provenance are visible wherever relevant.

## 12. Labs visual requirements (Release 2)

Labs are three guided experiences where machine learning results are shown honestly. They keep the homepage's cinematic restraint: image-led, dark, calm, with data drawn as thin lines on the same surfaces, not as a dashboard.

### 12.1 Labs landing page

- A curated mission gallery with one cover for each published Lab, up to three: Exoplanet Finder, Mars Terrain Lab and Mission Control. The Labs link and page appear with the first published Lab; an unpublished Lab gets no cover, no placeholder and no coming-soon card. Each cover has a title, a one-line premise, the data source it uses and a "Model results" label.
- Covers use real imagery where licensed (a Kepler field, a Mars rover image, a spacecraft) and a visualization label where it is not a direct observation. No generic AI wallpaper.
- One short, plain paragraph below the gallery explains what a Lab is: results are produced offline, reviewed by a person and published as a batch, and they are experiments, not discoveries.

### 12.2 Shared Lab page structure

Every Lab page uses the same order so the three feel like one family:

1. **Header:** Lab name, one-sentence purpose, the "Model prediction" label (section 14), model version and date.
2. **The experience:** the interactive item browser described below.
3. **How well does it work:** our own results shown next to the simple baseline, with the size of the test set and a short plain-language reading. Large numbers are never shown without their baseline.
4. **Limitations:** a visible section, not a footnote.
5. **Method and data:** data source and version, split rule, link to the full model card.
6. **Sources:** dataset and licence credits. Numbers from published papers appear only in a separate "From the literature" note and are never styled as our results.
A small badge on each item says whether it was in the training, validation or test data. Items from the training set are never presented as evidence of performance.

### 12.3 Exoplanet Finder

- **Signal browser:** a calm list or grid of curated Kepler signals, each with an identifier, period and a small folded light-curve thumbnail. Filters: predicted class, data split.
- **Signal view:** one large light-curve chart with the phase-folded dip highlighted; below it the prediction panel: predicted class, score, a plain explanation, and the catalogue's own label side by side. A signal predicted as a candidate is described as "looks like a planet-like transit", never as a planet.
- **Honest wording:** the panel states that the Finder cannot confirm planets or detect life.
- **Version 2 (CNN):** adds the global and local folded views as two small charts, and a toggle comparing the v1 and v2 predictions for the same signal.
- **Topic link:** appears only where an Exoverse topic exists for that signal.

### 12.4 Mars Terrain Lab

- **Image view:** a large rover image with a draggable comparison slider between the original image and the predicted terrain overlay, plus a tap-to-toggle alternative.
- **Class legend:** each terrain class has a name, a pattern and a color; the overlay is semi-transparent so the image stays readable. Colors are muted and distinct under common color-vision differences, and the legend is never the only way to tell classes apart.
- **Per-image notes:** the image's data split, and where an expert label exists, an option to flip between the prediction and the expert label.
- **Results:** per-class overlap scores shown as plain horizontal bars with labels, beside the baseline of predicting one class everywhere.
- **Credit:** rover, camera and dataset credit sits in the image caption.

### 12.5 Mission Control

- **Channel view:** a single telemetry chart with the real series as a thin line, the model's prediction as a second thin line, and the threshold as a dashed line. Anomaly events are shaded bands with a text label; flagged events and expert-labelled events use different treatments (outline versus fill) with a legend, not color alone.
- **Event list:** a short list beneath the chart; selecting an event zooms the chart to it. The page shows correct, missed and false-alarm events together.
- **Baseline comparison:** the same channel with the simple threshold method shown on request.
- **Framing:** an "Experimental, not a spacecraft safety system" note in the header. Series are downsampled for display and the page says so.
- **Tone:** quiet and instrument-like. No red alert styling, no sirens, no blinking.

### 12.6 Chart and data styling

- Charts sit on the surface color with the border token; lines are 1.5 px or thinner, with ice for the main series and violet for the comparison series. Amber is reserved for caution notes, never for "anomaly" alone.
- Axes and labels use the muted text token at a readable size; numbers use the monospace family from the type system; units are always shown.
- Tooltips are optional extras. Every important value is also visible without hover.
- Each chart has a data-table alternative and a one-sentence text summary.

## 13. AI assistant visual requirements (Release 2)

The assistant should feel like a research desk beside the page: quiet, sourced and clearly not an authority. From stage A (M11) it is reached only from an "Ask about this topic" control on topic pages. The Ask link in the navigation and the global Ask page arrive with stage C (M17).

### 13.1 Topic panel

- **Desktop:** a side panel that slides in from the right over about a third of the width, with the topic page still visible and readable beside it. **Mobile:** a bottom sheet that opens to half height and expands to full height.
- **Header:** the topic name the questions are about, the explanation level control, and a close button. The header carries an "AI-generated, based on Exoverse sources" mark (section 14).
- **Input:** one text field with a visible character limit and a submit button. Two or three suggested questions drawn from the topic, as plain text buttons.
- **No persona:** no avatar, name, mascot, emoji or greeting. The panel title is "Ask about this topic".

### 13.2 Explanation level control

- A three-option segmented control: Beginner, Intermediate, Advanced. Intermediate is selected by default; the choice is remembered for signed-in users.
- A one-line helper under the control describes the current level in plain words (for example, "Plain language, no equations").
- Changing the level offers to re-answer the last question; it never silently replaces an answer.

### 13.3 Answer presentation

- The answer appears as one block of short paragraphs, with numbered citation markers inside the text.
- A **Sources** list sits directly under the answer. Each source shows the topic or paper title, the section, the evidence label, the date and a link. Where a source is a paper, its peer-review status (peer-reviewed, preprint or unknown) is shown.
- Hovering a marker highlights the matching source; on touch, tapping scrolls to it. The same information is available without hover.
- Where sources disagree, the answer shows both positions with their labels. It never blends them into one figure.
- Claims are written at the level of evidence in the source: a hypothesis stays a hypothesis in the answer.
- A small "Report a problem with this answer" link sits under each answer.

### 13.4 States

| State | What the person sees |
| --- | --- |
| Loading | A calm placeholder with the words "Looking through sources"; no animated dots and no typewriter effect |
| Answered | The answer, sources and the AI-generated mark |
| Not in the sources | A plain statement that Exoverse's sources do not cover this, with suggestions to browse related topics. Never a guess |
| Daily limit reached | The remaining requests and the time they reset; the rest of the site is unaffected |
| Assistant paused (monthly budget) | "The assistant is paused until next month" with a link back to the topic; no apology theatrics |
| Unavailable | "The assistant is unavailable right now" and a retry action; the topic page keeps working |
| Signed out | An invitation to sign in; the question is not lost |

None of these states uses error red; the limit and paused states use the muted text style with an amber caution icon.

### 13.5 Global Ask page (stage C)

- A single wide, calm page: the same input and level control, answer and sources below, no topic context unless the person picks one.
- A short list of the corpus: Exoverse topics, scientific metadata and paper abstracts. Full paper text is not part of it, and the page says so in one line.
- Related Research Paper Explorer links appear beside the answer when papers were cited.

### 13.6 Research Paper Explorer visuals (stage C)

- Paper pages follow the topic page pattern: title, authors, venue, date, peer-review status chip, abstract, topics it relates to and related papers as a small connected set.
- AI summaries sit in a visibly separate panel with the AI-generated mark, the findings, and the limitations, and are collapsed by default under the abstract.
- The source and licence note for the paper is always visible.

## 14. Scientific label system

Four different questions are answered by four different marks, and they must never be merged or reused for one another.

### 14.1 Evidence label (how strong is this claim?)

Shown on topics, claims, experiences and assistant sources. Always text plus a distinct icon shape, never color alone.

| Label | Meaning | Icon shape (proposed) | Color role |
| --- | --- | --- | --- |
| Observed | Directly measured or seen | Filled square | Restrained green |
| Established | Well supported by many independent lines of evidence | Filled diamond | Restrained green, lighter |
| Theoretical | Follows from accepted theory, not yet tested | Open diamond | Restrained violet |
| Hypothesis | A proposed explanation that can be tested | Open triangle | Amber |
| Speculative | Imaginative or not currently testable | Dashed circle | Amber, stronger outline |

Each label has a one-sentence tooltip and an equivalent "What does this mean?" link to the scientific standards page, so the meaning is available on touch devices.

### 14.2 Research status chip (is it being actively studied?)

"Under investigation" is a separate, optional chip with a magnifier icon and neutral styling. It sits beside the evidence label, never replaces it and never changes its color. A topic can be Established and Under investigation at once.

### 14.3 Image class (what kind of picture is this?)

Carried over from the Homepage Breakdown and unchanged: Observed image (●), Scientific visualization (◐) and Artist's impression (○), shown in the image caption and as a small corner mark on cards. A hypothetical simulation uses the visualization mark with the word "Hypothetical". These marks describe pictures only.

### 14.4 Value kind (how was this number obtained?)

A small text marker beside each key fact: Measured, Derived, Estimated, Theoretical or Model prediction, with a plain tooltip. Uncertainty is shown with the value wherever it exists.

### 14.5 Model prediction label (a machine produced this)

- Used wherever a result comes from one of the Labs, including predicted values, class labels, segmentation overlays and anomaly flags.
- Style: a dashed outline chip with a small model glyph and the text "Model prediction", plus the model version (for example, "Model prediction · v1"). Dashed outlines mean "computed, not observed" everywhere on the site.
- It sits next to the result, not only in the page header, and it is never removed when a result is embedded elsewhere, such as on a topic page.
- Never combined with the green "Observed" or "Established" styling.

### 14.6 AI-generated mark

A small chip reading "AI-generated" with a spark icon, in the same dashed-outline family as the model prediction label, on every assistant answer and paper summary. It is accompanied by "Based on Exoverse sources" where citations exist.

### 14.7 How the marks combine

| Situation | Marks shown |
| --- | --- |
| A measured planet radius | Value kind Measured; the topic's evidence label |
| A planet property estimated by a model | Value kind Model prediction, with the model prediction chip and version |
| A Finder result for a signal | Model prediction chip with version; data split badge; the catalogue label shown separately as the dataset's own label |
| A Mars overlay | Model prediction chip; image class mark for the underlying rover photo (Observed image) |
| An assistant answer | AI-generated chip; each cited source with its own evidence label and date |
| A speculative concept scene | Speculative label; Artist's impression or Hypothetical visualization mark |

## 15. Additional acceptance checks (added in v2)

- The approved homepage is unchanged apart from the wordmark and two navigation links, and the hero astronaut remains the visual signature.
- Labs and the assistant look like part of the same site: same tokens, same type, same restraint.
- Every Lab result carries the Model prediction label, the model version, the data split and a link to Limitations.
- Our results always appear next to a baseline, and numbers from papers are never shown as ours.
- No model score is presented as a probability of life, habitability or confirmation.
- Every assistant answer shows citations that resolve to real sources, the AI-generated mark, and a level control; unanswerable questions show the "not in the sources" state.
- Limit, paused, unavailable and signed-out states are designed and tested.
- The five evidence labels use distinct shapes and text; the research status chip is visually separate from them.
- All charts have text summaries and data-table alternatives; segmentation classes are separable without color.
- Labs and the assistant are checked at mobile width, with keyboard only, with reduced motion and with a screen reader.
- No feature other than the three Labs and the assistant appears anywhere in navigation, copy or imagery, and the navigation lists only destinations that exist: no Labs, Ask or My Universe link before its release.

## 16. Open items

| ID | Item | Status |
| --- | --- | --- |
| V1 | Hero astronaut pose and source | Decided 24 November 2026: behind or three-quarter, custom 3D render, layered images (Homepage Breakdown v0.2) |
| V2, V3 | Other homepage decisions as listed in the Homepage Breakdown | Open |
| V4 | Pull-back built from pre-rendered layers or live 3D | Decided 25 November 2026: pre-rendered AVIF and WebP layers, no WebGL (Homepage Breakdown v0.2) |
| V5 | Ember accent color | Not approved; nothing here depends on it |
| VG-Q4 | Chart library for Lab charts (decide at M12; prefer one that supports accessible data tables) | Open |
| VG-Q5 | Final segmentation class colors and patterns, checked for color-vision differences | Decide at M15 |
| VG-Q6 | Assistant panel behavior on mid-size tablets (side panel versus sheet) | Decide at M11 |
| VG-Q7 | Icon shapes for the five evidence labels, confirmed in design review | Decide when the label component is built |

No visual work for Release 2 is needed before M8; these questions are recorded so they are not forgotten.
