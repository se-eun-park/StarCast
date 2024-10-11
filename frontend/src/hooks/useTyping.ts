/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from 'react'

const useTyping = (content: string) => {
  const [text, setText] = useState('\u200B')
  const [characterCount, setCharacterCount] = useState(0)

  useEffect(() => {
    const typeEvent = setInterval(() => {
      setCharacterCount((prev) => prev + 1)
      setText((prev) => prev + content[characterCount])
    }, 75)

    if (text.length > content.length) {
      clearInterval(typeEvent)
    }

    return () => clearInterval(typeEvent)
  }, [text])

  const reset = () => {
    setText('\u200B')
    setCharacterCount(0)
  }

  return { text, reset }
}

export default useTyping
