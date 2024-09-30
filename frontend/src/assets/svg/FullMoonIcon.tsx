import type { SVGProps } from 'react'
const SvgFullMoonIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns='http://www.w3.org/2000/svg'
    xmlnsXlink='http://www.w3.org/1999/xlink'
    fill='none'
    viewBox='0 0 30 30'
    {...props}
  >
    <path fill='url(#full-moon-icon_svg__a)' d='M0 0h30v30H0z' />
    <defs>
      <pattern
        id='full-moon-icon_svg__a'
        width={1}
        height={1}
        patternContentUnits='objectBoundingBox'
      >
        <use xlinkHref='#full-moon-icon_svg__b' transform='scale(.01)' />
      </pattern>
      <image
        xlinkHref='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAACXBIWXMAAAsTAAALEwEAmpwYAAADx0lEQVR4nO3dS29NURTA8e0RWhOhrQRpGWFuipAUoeoVfIQ2NKrx+A4GiHoMShj4AI3HqBg1xKzUa0JLlU5otTMG+pcVWzwGtz3ndp+999nrl3Ry783tOWflnv1c6xijlFJKKaWUUkoplQmwAtgJdAJXgYfAc2AImAC+278J+5q898B+9jiwA2jQy54TsATYB3TbiztN9eQ7BoGLwF6gVgNUOQjzgU1ADzCFe/I/bgGtwAINzp9A1ADHgGH8kdvcUWBx6rel08AY4RgDTsqxmZTY28Q7wjUKHDJlB6wF7hGPO0CTKSNgv+2WxmYKOGLKQhpK232NXU/0jT5QBzyhPB4By02MgFV2UFc2r4FGExNgA/CB8hoB1psYAKuB95TfR2CNiaDNkJ90Kl4F26bY3lSZGvDZehxk7wu4Qrq6TUhkmsH3FQnAQRPQdMik76sRgK9BNPLAXd9XIiC3Q5ifUv9q9bmekcJ4I6thL8vDwJnMh5qOLh9jjk++zzpgsvJYU2RAZA1cVdZe5O4QnxsSYvEWmFdEQLb5PtOIbCkiIDd9n2VErrsORq2OyjOvx7vrAtvtnSqbFpcBKcNmhaKdcxmQMq6Ru/bUZUrAXOxCT80PoN5FQCQ/Q+XT7CIgkiyj8ulwERDJRlL5XHIREEkRU/n0uQjIi5wHo2DQRUB0MSq/YRcBGa/igFL32UVAJO1Y5fNNA5JAQPSWFdgtSxv1wBp17fYG1u3VgWFgA0OdOgls6kQnFwObXNTp98Cm3xt0gSr3AlXdnAfEBkWXcLMbcBIMGxAp/qUC2uQgldhUNrtdBkQ3ymUz6TxXBLiR8aBSds1pMGxAtvo+y4hsLiodQWoUqsreFJKOYIMiBSNVZW2FBMMGRFPaZq7ZWGy5DeDUDAeVss5Cg/FXWnTIlUV9prIVl/D5X1B2eTvtFPNBZhkUKaWqfun1GgwbkCZbeCV1E8HUYgT2JD41Pw0cMCFJPN3tggmNHZtIubvU9AOLTIiApcAz0vEy2CKYCZaJHY2mUL8UGbbFhstqBFhnYgKstM98KmOt3kYTI7m/lqyh7weWmZgBC4GzkY9Tpm23PszeVBV1UmJ9oMthU0ZS1zayua/eaNuLHFMtIVelG/I+a1s0u6Woyz72IaSxxQlv6xkBTbm024UdnxsS2krVaM8FYKPtzXwpaBObPHq1ubDdIZE/jrVFZlGl3pTdQV4t+Y4B4Lxs70z6tlQtoB7YLokvwGXgvp3EHLLZwb8f3z1uX5P3+uxnO+yvwE1KgFJKKaWUUkoppUx5/QRUcNEioUpvZQAAAABJRU5ErkJggg=='
        id='full-moon-icon_svg__b'
        width={100}
        height={100}
      />
    </defs>
  </svg>
)
export default SvgFullMoonIcon
