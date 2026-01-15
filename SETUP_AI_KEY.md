# 🔑 How to Get & Configure Your OpenAI API Key

To upgrade your Property Search from "Regex Mode" to "Full AI Intelligence", follow these steps.

## 1. Get the Key
1.  Go to **[OpenAI API Keys](https://platform.openai.com/api-keys)**.
2.  Click **+ Create new secret key**.
3.  Name it `Property App` and copy the code (starts with `sk-...`).
4.  *Note: Ensure your OpenAI account has billing set up (pre-paid credits), otherwise the API will return errors.*

## 2. Configure the App
Create a file named `.env` in this directory (`/Users/deepakwagadre/.gemini/antigravity/playground/ruby-juno/.env`) and paste your key:

```env
OPENAI_API_KEY=sk-example123456789...
```

## 3. Activate AI Mode
Run this command in your terminal to apply the new key:

```bash
docker-compose up -d --build ai-agents
```

## 4. Verify
After restarting, try a search like:
> *"I want a quiet place for my elderly parents near a park"*

The AI will now understand "quiet", "elderly parents", and "park" to verify the search, whereas the Regex mode previously would ignore these subtle clues.
