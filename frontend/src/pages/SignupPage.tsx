import SignupButton from '@components/signup/SignupButton'
import { StarcastLogoIcon } from '@assets/svg'
import SignupInput from '@components/signup/SignupInput'
import SignupModal from '@modal/signup/SignupModal'

const SignupPage = () => {
  return (
    <div className='p-6 pt-6 pb-6 px-4'>
      <StarcastLogoIcon className='w-[9rem] h-[2rem] mb-6' />
      <div className='pl-1 gap-1'>
        <p className='text-xl font-paperlogy'>하나만 물어볼게요!</p>
        <p className='text-md font-paperlogy'>닉네임만 정하면 가입이 완료돼요.</p>
      </div>
      <SignupInput
        signupInput={''}
        setSignupInput={function (): void {
          throw new Error('Function not implemented.')
        }}
      />
      <SignupButton isValid={false} />
      <SignupModal />
    </div>
  )
}

export default SignupPage
